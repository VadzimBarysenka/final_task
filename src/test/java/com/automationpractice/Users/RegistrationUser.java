package com.automationpractice.Users;

public class RegistrationUser {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String address;
  private String city;
  private String postcode;
  private String phoneMobile;
  private String state;

  public RegistrationUser(String firstName, String lastName, String email, String password, String address, String city, String postcode, String phoneMobile, String state) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.city = city;
    this.postcode = postcode;
    this.phoneMobile = phoneMobile;
    this.state = state;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getPostcode() {
    return postcode;
  }

  public String getPhoneMobile() {
    return phoneMobile;
  }

  public String getState() {
    return state;
  }
}

