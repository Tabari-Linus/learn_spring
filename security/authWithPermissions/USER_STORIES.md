# Spring Security Project — User Stories

## Table of Contents

1. [Project Setup](#project-setup)
2. [Data Model](#data-model)
3. [Registration](#registration)
4. [Login](#login)
5. [JWT Filter & Security Context](#jwt-filter--security-context)
6. [Security Configuration](#security-configuration)
7. [Logout](#logout)
8. [Password Reset](#password-reset)
9. [Email Service](#email-service)
10. [Authorization & Permissions](#authorization--permissions)
11. [Implementation Order](#implementation-order)

---

## Project Setup

### US-01: Project Dependencies
> As a developer, I want the project to include the following dependencies so that all security, database, and email features are available:
> - `spring-boot-starter-security`
> - `spring-boot-starter-web`
> - `spring-boot-starter-data-jpa`
> - `spring-boot-starter-mail`
> - `spring-boot-starter-validation`
> - `jjwt` (or `java-jwt`) for JWT handling
> - `postgresql` (or your chosen DB driver)

---

## Data Model

### US-02: Permission Entity
> As a developer, I want a `Permission` entity with fields `id`, `name` (e.g., `VIEW_REPORT`), and `groupName` (e.g., `REPORTS`) stored in a `permissions` table so that permissions can be managed in the database.

### US-03: Permission Enum
> As a developer, I want a `PermissionName` enum that lists every valid permission string in the system so that `@Secured` annotations and permission checks are type-safe and centralized.

### US-04: Access Level Entity
> As a developer, I want an `AccessLevel` entity with fields `id` and `name` (e.g., `Administrator`, `Finance Manager`) that holds a list of `Permission` objects so that permissions are grouped into roles.

### US-05: Organization Entity
> As a developer, I want an `Organization` entity with fields `id` and `name` so that users and permissions can be scoped to a specific organization.

### US-06: Organization Office Entity
> As a developer, I want an `OrganizationOffice` entity with fields `id`, `name`, and a reference to an `Organization` so that an organization can have multiple offices.

### US-07: Permission-Office Scope Entity
> As a developer, I want a `PermissionScope` entity that links a `Permission`, an `AccessLevel`, and a list of `OrganizationOffice` objects so that a user's permission can be restricted to specific offices within an organization.

### US-08: User Entity
> As a developer, I want a `User` entity with the following fields stored in a `users` table:
> - `id` (UUID)
> - `firstName`, `lastName`
> - `email` (unique)
> - `password` (hashed)
> - `isEnabled` (boolean, default `false`)
> - `accessLevel` (FK to `AccessLevel`)
> - `organizationOffice` (FK to `OrganizationOffice`)
> - `createdAt`, `updatedAt`

### US-09: Email Verification Token Entity
> As a developer, I want an `EmailVerificationToken` entity with fields `id`, `token` (UUID string), `user` (FK), `expiresAt`, and `used` (boolean) stored in a `email_verification_tokens` table so that email verification links can be tracked and expired.

### US-10: Password Reset Token Entity
> As a developer, I want a `PasswordResetToken` entity with fields `id`, `token` (UUID string), `user` (FK), `expiresAt`, and `used` (boolean) stored in a `password_reset_tokens` table so that password reset links can be tracked.

---

## Registration

### US-11: User Registration Endpoint
> As a new user, I want to call `POST /api/auth/register` with `firstName`, `lastName`, `email`, and `password` so that an account is created for me.
>
> **Acceptance criteria:**
> - All fields are required and validated (valid email format, password min 8 chars)
> - Returns `201 Created` with a message saying to check email
> - Returns `409 Conflict` if the email already exists
> - The user's `isEnabled` is set to `false` until email is verified
> - Password is stored hashed using `BCryptPasswordEncoder`

### US-12: Default Access Level on Registration
> As the system, when a new user registers, I should automatically assign them a default `AccessLevel` (e.g., `Viewer`) so that they have the minimum permissions until an administrator promotes them.

### US-13: Email Verification Token Generation
> As the system, immediately after a user registers, I should generate a unique UUID token, save it to `EmailVerificationToken` with a 24-hour expiry, and send it to the user's email.

### US-14: Email Verification Email
> As a newly registered user, I should receive an email with a verification link in the format:
> ```
> https://<domain>/api/auth/verify-email?token=<uuid>
> ```
> so that I can activate my account.

### US-15: Email Verification Endpoint
> As a registered user, I want to call `GET /api/auth/verify-email?token=<uuid>` so that my account is activated.
>
> **Acceptance criteria:**
> - Returns `200 OK` and sets `isEnabled = true` on the user if the token is valid and not expired
> - Returns `400 Bad Request` with a message if the token is expired or already used
> - Returns `404 Not Found` if the token does not exist
> - Marks the token as `used = true` after successful verification

### US-16: Resend Verification Email
> As a user whose verification email expired, I want to call `POST /api/auth/resend-verification` with my `email` so that a new verification token is generated and sent.
>
> **Acceptance criteria:**
> - Invalidates any existing unused tokens for that user
> - Generates a new token with a fresh 24-hour expiry
> - Returns `200 OK` regardless of whether the email exists (to prevent user enumeration)

---

## Login

### US-17: Login Endpoint
> As a registered and verified user, I want to call `POST /api/auth/login` with my `email` and `password` so that I receive a JWT to use for subsequent requests.
>
> **Acceptance criteria:**
> - Returns `200 OK` with a JSON body containing `accessToken` and `expiresIn` (seconds)
> - Returns `401 Unauthorized` if credentials are wrong
> - Returns `403 Forbidden` with a message `"Email not verified"` if `isEnabled` is `false`
> - Password is verified using `BCryptPasswordEncoder`

### US-18: JWT Generation
> As the system, when a user successfully logs in, I should generate a JWT that contains the following claims:
> - `sub`: user ID (UUID)
> - `email`: user's email
> - `iat`: issued-at timestamp
> - `exp`: expiry timestamp (e.g., 1 hour from now)
>
> The token must be signed with an RSA private key.

### US-19: RSA Key Configuration
> As a developer, I want the RSA private key (for signing) and public key (for verifying) to be loaded from environment variables or `application.yml` so that keys are never hardcoded in source code.

---

## JWT Filter & Security Context

### US-20: JWT Authentication Filter
> As the system, I want a `JwtAuthenticationFilter` that runs once per request and:
> 1. Extracts the token from the `Authorization: Bearer <token>` header
> 2. Verifies the token signature using the RSA public key
> 3. Extracts the `user_id` from the token
> 4. Loads the user's permissions from the database via `UserService`
> 5. Sets a `UsernamePasswordAuthenticationToken` in the `SecurityContextHolder`
>
> If any step fails, the filter should clear the context and let the request continue unauthenticated (the endpoint's security rules will then reject it).

### US-21: Unauthenticated Request Handler
> As the system, I want an `AuthEntryPoint` that implements `AuthenticationEntryPoint` so that any request reaching a secured endpoint without a valid JWT receives a `401 Unauthorized` JSON response instead of a redirect.

### US-22: Access Denied Handler
> As the system, I want an `AccessDeniedHandlerImpl` that implements `AccessDeniedHandler` so that any authenticated user who accesses an endpoint they lack permission for receives a `403 Forbidden` JSON response with a meaningful message.

---

## Security Configuration

### US-23: Security Config
> As a developer, I want a `SecurityConfig` class annotated with `@EnableMethodSecurity(securedEnabled = true)` that:
> - Disables CSRF (stateless JWT API)
> - Sets session management to `STATELESS`
> - Permits `POST /api/auth/register`, `POST /api/auth/login`, `GET /api/auth/verify-email`, `POST /api/auth/resend-verification`, and `POST /api/auth/forgot-password` without authentication
> - Requires authentication on all other endpoints
> - Registers the `JwtAuthenticationFilter` before `UsernamePasswordAuthenticationFilter`
> - Registers the `AuthEntryPoint` and `AccessDeniedHandlerImpl`

### US-24: Password Encoder Bean
> As a developer, I want a `BCryptPasswordEncoder` bean defined in `SecurityConfig` so that it can be injected wherever passwords need to be hashed or verified.

### US-25: Authentication Manager Bean
> As a developer, I want an `AuthenticationManager` bean exposed in `SecurityConfig` so that it can be injected into the login service to authenticate credentials programmatically.

---

## Logout

### US-26: Token Blacklist Entity
> As a developer, I want a `TokenBlacklist` entity with fields `id`, `token` (the full JWT string or its JTI), and `expiresAt` so that logged-out tokens can be tracked and rejected.

### US-27: Logout Endpoint
> As an authenticated user, I want to call `POST /api/auth/logout` with my JWT in the `Authorization` header so that my token is invalidated server-side.
>
> **Acceptance criteria:**
> - Adds the token to `TokenBlacklist`
> - Returns `200 OK` with a message `"Logged out successfully"`
> - Returns `401 Unauthorized` if no valid token is present

### US-28: Blacklist Check in JWT Filter
> As the system, when the `JwtAuthenticationFilter` processes a token, it must check the `TokenBlacklist` before setting the `SecurityContext`. If the token is blacklisted, it should be treated as invalid and the request should proceed unauthenticated.

### US-29: Blacklist Cleanup Job
> As a developer, I want a scheduled task (e.g., `@Scheduled(cron = "0 0 * * * *")`) that deletes all `TokenBlacklist` entries whose `expiresAt` is in the past so that the table does not grow unbounded.

---

## Password Reset

### US-30: Forgot Password Endpoint
> As a user who forgot their password, I want to call `POST /api/auth/forgot-password` with my `email` so that I receive a password reset link.
>
> **Acceptance criteria:**
> - Always returns `200 OK` regardless of whether the email exists (to prevent user enumeration)
> - If the email exists, generates a `PasswordResetToken` with a 1-hour expiry and sends a reset email

### US-31: Password Reset Email
> As a user, I should receive an email with a password reset link in the format:
> ```
> https://<domain>/api/auth/reset-password?token=<uuid>
> ```
> so that I can set a new password.

### US-32: Reset Password Endpoint
> As a user, I want to call `POST /api/auth/reset-password` with `token` and `newPassword` so that my password is updated.
>
> **Acceptance criteria:**
> - Returns `200 OK` if the token is valid, not expired, and not used
> - Hashes the new password with `BCryptPasswordEncoder` before saving
> - Marks the token as `used = true`
> - Returns `400 Bad Request` if the token is expired, already used, or invalid

---

## Email Service

### US-33: Email Service
> As a developer, I want an `EmailService` that uses `JavaMailSender` to send HTML emails so that verification and password reset links are delivered with proper formatting.

### US-34: Email Templates
> As a developer, I want separate HTML email templates (using Thymeleaf or plain HTML strings) for:
> - Account verification
> - Password reset
>
> so that emails are readable and on-brand.

### US-35: Mail Configuration
> As a developer, I want SMTP settings (`host`, `port`, `username`, `password`, `auth`, `starttls`) configured in `application.yml` from environment variables so that no credentials are hardcoded.

---

## Authorization & Permissions

### US-36: Permission Loading Service
> As the system, I want a `UserService.getUserPermissions(UUID userId)` method that queries `PermissionScope` for all permissions linked to the user's `AccessLevel` and returns them as a list of `GrantedAuthority` objects so that Spring Security can enforce them.

### US-37: Method-Level Security on Endpoints
> As a developer, I want to annotate controller methods with `@Secured("PERMISSION_NAME")` using values from `PermissionName` enum so that only users whose `AccessLevel` includes that permission can call the endpoint.
>
> Example:
> ```java
> @Secured("MANAGE_USERS")
> @PostMapping("/admin/users")
> public ResponseEntity<?> createUser(...) { ... }
> ```

### US-38: Admin: Assign Access Level
> As an admin user with the `MANAGE_ACCESS_LEVELS` permission, I want to call `PUT /api/admin/users/{userId}/access-level` with an `accessLevelId` so that I can promote or demote a user's role.

### US-39: Admin: Create Access Level
> As an admin user with the `MANAGE_ACCESS_LEVELS` permission, I want to call `POST /api/admin/access-levels` with a `name` and a list of `permissionIds` so that I can define new roles in the system.

---

## Implementation Order

```
Phase 1 — Entities & Repositories
  Permission → AccessLevel → Organization → OrganizationOffice
  → PermissionScope → User → EmailVerificationToken
  → PasswordResetToken → TokenBlacklist

Phase 2 — Email Service
  MailConfig → EmailService → templates

Phase 3 — Registration & Verification
  UserService → AuthService → AuthController (register, verify, resend)

Phase 4 — JWT Infrastructure
  RSA key config → JwtService (sign/verify/extract)
  → JwtAuthenticationFilter → AuthEntryPoint → AccessDeniedHandlerImpl

Phase 5 — Login & Logout
  AuthService (login, logout) → TokenBlacklist check in filter
  → cleanup job

Phase 6 — Password Reset
  AuthService (forgot, reset) → AuthController

Phase 7 — Authorization
  UserService.getUserPermissions() → SecurityConfig method security
  → @Secured on controller methods → admin endpoints
```
