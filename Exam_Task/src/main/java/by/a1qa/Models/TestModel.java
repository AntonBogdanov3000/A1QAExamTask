package by.a1qa.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestModel {

    public static String testName;
    public static String methodName;
    public static String testId;

    private String name;
    private String method;
    private String status;
    private String startTime;
    private String endTime;
    private String duration;

}
