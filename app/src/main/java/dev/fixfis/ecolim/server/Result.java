package dev.fixfis.ecolim.server;

public class Result {
    int error;
    String message;

    Object data;

    private Result(int error, String message) {
        this.error = error;
        this.message = message;
    }

    public static Result error(String message){
        return new Result(1,message);
    }
    public static Result success(){
        return new Result(0,"");
    }

    /**
     *   "timestamp": "2026-03-08T04:25:00.316Z",
     *   "status": 404,
     *   "error": "Not Found",
     *   "path": "/crerol/login"
     */


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

    @Override
    public String toString() {
        return "Result{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
