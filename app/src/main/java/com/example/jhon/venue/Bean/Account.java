package com.example.jhon.venue.Bean;

/**
 * Created by John on 2017/3/7.
 */

public class Account {


    /**
     * isError : false
     * errorMessage :
     * result : {"token":"4c62b0707a104de76ab68ce5704ef74fadcd4c8b2e051f0f4f0e5ef10e61c5fa","type":"normal","expiration":1489497638898}
     */

    private boolean isError;
    private String errorMessage;
    private ResultBean result;

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * token : 4c62b0707a104de76ab68ce5704ef74fadcd4c8b2e051f0f4f0e5ef10e61c5fa
         * type : normal
         * expiration : 1489497638898
         */

        private String token;
        private String type;
        private long expiration;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getExpiration() {
            return expiration;
        }

        public void setExpiration(long expiration) {
            this.expiration = expiration;
        }
    }
}
