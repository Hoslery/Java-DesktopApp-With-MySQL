package sample.model;

import javafx.beans.property.*;


public class Organization {
    private IntegerProperty org_id;
    private StringProperty org_name;
    private StringProperty activity;
    private StringProperty email;
    private StringProperty phone_number;
    private StringProperty phone_functionality;
    private StringProperty dir_surname;
    private StringProperty service;
    private DoubleProperty price;
    private StringProperty country;
    private StringProperty city;
    private StringProperty street;
    private StringProperty fromTime;
    private StringProperty upTime;
    private StringProperty weekend;
    private StringProperty dayOfWeek;
    private StringProperty old_number;
    private StringProperty date_add;


    //Constructor
    public Organization() {
        this.org_id = new SimpleIntegerProperty();
        this.org_name = new SimpleStringProperty();
        this.activity = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone_number = new SimpleStringProperty();
        this.phone_functionality = new SimpleStringProperty();
        this.dir_surname = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.country = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.service = new SimpleStringProperty();
        this.fromTime = new SimpleStringProperty();
        this.upTime = new SimpleStringProperty();
        this.weekend = new SimpleStringProperty();
        this.dayOfWeek = new SimpleStringProperty();
        this.old_number = new SimpleStringProperty();
        this.date_add = new SimpleStringProperty();
    }


    public int getOrg_id() {
        return org_id.get();
    }

    public IntegerProperty org_idProperty() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id.set(org_id);
    }

    public String getOrg_name() {
        return org_name.get();
    }

    public StringProperty org_nameProperty() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name.set(org_name);
    }

    public String getActivity() {
        return activity.get();
    }

    public StringProperty activityProperty() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity.set(activity);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public StringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }

    public String getPhone_functionality() {
        return phone_functionality.get();
    }

    public StringProperty phone_functionalityProperty() {
        return phone_functionality;
    }

    public void setPhone_functionality(String phone_functionality) {
        this.phone_functionality.set(phone_functionality);
    }

    public String getDir_surname() {
        return dir_surname.get();
    }

    public StringProperty dir_surnameProperty() {
        return dir_surname;
    }

    public void setDir_surname(String dir_surname) {
        this.dir_surname.set(dir_surname);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getService() {
        return service.get();
    }

    public StringProperty serviceProperty() {
        return service;
    }

    public void setService(String service) {
        this.service.set(service);
    }

    public String getFromTime() {
        return fromTime.get();
    }

    public StringProperty fromTimeProperty() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime.set(fromTime);
    }

    public String getUpTime() {
        return upTime.get();
    }

    public StringProperty upTimeProperty() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime.set(upTime);
    }

    public String getWeekend() {
        return weekend.get();
    }

    public StringProperty weekendProperty() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend.set(weekend);
    }

    public String getDayOfWeek() {
        return dayOfWeek.get();
    }

    public StringProperty dayOfWeekProperty() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek.set(dayOfWeek);
    }

    public String getOld_number() {
        return old_number.get();
    }

    public StringProperty old_numberProperty() {
        return old_number;
    }

    public void setOld_number(String old_number) {
        this.old_number.set(old_number);
    }

    public String getDate_add() {
        return date_add.get();
    }

    public StringProperty date_addProperty() {
        return date_add;
    }

    public void setDate_add(String date_add) {
        this.date_add.set(date_add);
    }
}
