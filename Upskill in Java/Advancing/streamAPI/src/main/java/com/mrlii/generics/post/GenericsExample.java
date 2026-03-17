package com.mrlii.generics.post;

import java.util.List;

// 1️⃣ THE PROBLEM: Duplicated Code for Different Types

class UserProfileWrapper {
    private UserProfile data;
    // Constructor, Getters...
}

class UserOrdersWrapper {
    private UserOrders data;
    // Constructor, Getters...
}

class UserWrapper {
    private User data;
    // Constructor, Getters...
}

// Imagine needing ProductWrapper, CartWrapper...
// Same logic, same structure, just different types. 😫

// 2️⃣ THE SOLUTION: One Generic Class for ALL Types

class Wrapper<T> {
    private T data;

    public Wrapper(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

// Usage:
// Wrapper<UserProfile> profileWrapper = new Wrapper<>(new UserProfile());
// Wrapper<UserOrders> ordersWrapper = new Wrapper<>(new UserOrders());
// Wrapper<User> userWrapper = new Wrapper<>(new User());

// 3️⃣ REAL-WORLD EXAMPLE: API Responses

record ApiResponse<T>(boolean success, String message, T data) {}

class UserProfile {}
class UserOrders {}
class User {}
class Product {}
class Order {}


// 4️⃣ CONTROLLER EXAMPLES: Using ApiResponse in Real APIs

class UserController {

    public ApiResponse<User> getUserById(String userId) {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.findById(userId);
        return new ApiResponse<>(true, "User retrieved successfully", user);
    }

    public ApiResponse<UserProfile> getUserProfile(String userId) {
        UserProfile profile = new UserProfile(); // Simulate profile fetch
        return new ApiResponse<>(true, "Profile loaded", profile);
    }

    public ApiResponse<List<UserOrders>> getUserOrders(String userId) {
        List<UserOrders> orders = List.of(new UserOrders()); // Simulate orders fetch
        return new ApiResponse<>(true, "Orders retrieved", orders);
    }

    public ApiResponse<String> deleteUser(String userId) {
        // Simulate deletion
        return new ApiResponse<>(true, "User deleted successfully", userId);
    }
}

class ProductController {

    public ApiResponse<Product> getProductById(String productId) {
        Product product = new Product(); // Simulate product fetch
        return new ApiResponse<>(true, "Product found", product);
    }

    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = List.of(new Product(), new Product()); // Simulate fetch
        return new ApiResponse<>(true, "Products retrieved", products);
    }

    public ApiResponse<Product> createProduct(Product product) {
        // Simulate product creation
        return new ApiResponse<>(true, "Product created successfully", product);
    }
}

class OrderController {

    public ApiResponse<Order> placeOrder(Order order) {
        // Simulate order placement
        return new ApiResponse<>(true, "Order placed successfully", order);
    }

    public ApiResponse<List<Order>> getOrderHistory(String userId) {
        List<Order> orders = List.of(new Order()); // Simulate fetch
        return new ApiResponse<>(true, "Order history retrieved", orders);
    }

    public ApiResponse<Void> cancelOrder(String orderId) {
        // Simulate order cancellation (no data to return)
        return new ApiResponse<>(true, "Order cancelled", null);
    }
}