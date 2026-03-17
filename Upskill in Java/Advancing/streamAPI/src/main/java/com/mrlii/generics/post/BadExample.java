package com.mrlii.generics.post;

// ❌ THE "BEFORE" SCENARIO: CODE DUPLICATION
// I used to write separate classes for every type I needed to handle.
// It was repetitive and hard to maintain.

class StringResponse {
    private String data;
    public StringResponse(String data) { this.data = data; }
    public String getData() { return data; }
}

class IntegerResponse {
    private Integer data;
    public IntegerResponse(Integer data) { this.data = data; }
    public Integer getData() { return data; }
}

// If I needed a DoubleResponse or UserResponse, 
// I had to copy-paste the entire class again! 😫