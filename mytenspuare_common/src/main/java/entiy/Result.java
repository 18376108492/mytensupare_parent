package entiy;

/**
 * 十次方项目结果集
 */
public class Result {

    private boolean flag;
    private Integer code;//状态码
    private String message;//返回的信息
    private Object data;//返回的数据

    /**
     * 无参数构造器
     */
    public Result() {
    }


    /**
     * 不需要返回数据的构造器
     * @param flag
     * @param code
     * @param message
     */
    public  Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }


    /**
     * 需要返回数据的构造器
     * @param flag
     * @param code
     * @param message
     * @param data
     */
    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 查询成功，不需要携带参数的方法（默认消息：查询成功）
     * @return
     */
    public static Result ok(){
        return new Result(true,StatusCode.OK,"查询成功");
    }

    /**
     * 查询成功，需要携带数据消息参数的方法
     * @param message
     * @return
     */
    public static  Result ok(String message){
        return new Result(true,StatusCode.OK,message);
    }

    /**
     * 查询成功，需要携带数据参数的方法
     * @param data
     * @return
     */
    public static   Result ok(Object data){
        return new Result(true,StatusCode.OK,"查询成功",data);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
