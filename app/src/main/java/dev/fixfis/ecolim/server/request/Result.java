package dev.fixfis.ecolim.server.request;

public class Result {
    int error;
    String message;

    Object data;

    private Result(int error, String message) {
        this.error = error;
        this.message = message;
    }
    public static Result error(){
        return new Result(1,"error");
    }
    public static Result error(String message){
        return new Result(1,message);
    }
    public static Result success(){
        return new Result(0,"succes");
    }
    public static Result success(String msg){
        return new Result(0,msg);
    }

    /// ////////////////////////////////


    public int getError() {
        return error;
    }

    public Result setError(int error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
