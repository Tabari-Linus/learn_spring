package com.mrlii.generics.post;

// ✅ THE "AFTER" SOLUTION: GENERICS
// By introducing a type parameter <T>, I can use a single class for ANY data type.

class Response<T> {
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

// Now I can reuse the logic cleanly! 🎉
// Response<String> stringResp = new Response<>("Success!");
// Response<Integer> intResp = new Response<>(200);
// Response<User> userResp = new Response<>(new User());