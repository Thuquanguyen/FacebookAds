package com.example.facebookads.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posts {
    @SerializedName("results")
    @Expose
    private ResultsEntity results;

    public void setResults(ResultsEntity results) {
        this.results = results;
    }

    public ResultsEntity getResults() {
        return results;
    }

    public static class ResultsEntity {
        @SerializedName("allAccount")
        @Expose
        private int allAccount;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("cookie")
        @Expose
        private String cookie;
        @SerializedName("updated_at")
        @Expose
        private String updated_at;
        @SerializedName("listData")
        @Expose
        private String listData;
        @SerializedName("__v")
        @Expose
        private int __v;
        @SerializedName("created_at")
        @Expose
        private String created_at;
        @SerializedName("_id")
        @Expose
        private String blance;
        @SerializedName("blance")
        @Expose
        private String _id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("token")
        @Expose
        private String token;

        public String getBlance() {
            return blance;
        }

        public void setBlance(String blance) {
            this.blance = blance;
        }

        public void setAllAccount(int allAccount) {
            this.allAccount = allAccount;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setListData(String listData) {
            this.listData = listData;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getAllAccount() {
            return allAccount;
        }

        public String getPassword() {
            return password;
        }

        public String getCookie() {
            return cookie;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getListData() {
            return listData;
        }

        public int get__v() {
            return __v;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String get_id() {
            return _id;
        }

        public String getUsername() {
            return username;
        }

        public String getToken() {
            return token;
        }

        @Override
        public String toString() {
            return "ResultsEntity{" +
                    "blance=" +blance+
                    "allAccount=" + allAccount +
                    ", password='" + password + '\'' +
                    ", cookie='" + cookie + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", listData='" + listData + '\'' +
                    ", __v=" + __v +
                    ", created_at='" + created_at + '\'' +
                    ", _id='" + _id + '\'' +
                    ", username='" + username + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResultAdsModel{" +
                "results=" + results.toString() +
                '}';
    }
}
