package by.a1qa.Utilities;

import by.a1qa.Models.TestModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SortUtils {

    private static final Logger log = LogManager.getLogger(SortUtils.class);

    public static boolean isSortedByDate(List<TestModel> models){
        String pattern = "yyyy-MM-dd HH:mm:ss.S";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date lastDate;
        Date before;
        boolean result = true;
        try {
            for (int i = 0; i < models.size() - 1; i++) {

                lastDate = format.parse(models.get(i).getStartTime());
                before = format.parse(models.get(i+1).getStartTime());

                if (lastDate.after(before)){
                }else{
                    result = false;
                    break;
                }
            }
        }catch (java.text.ParseException e){
            log.error(e.getMessage());
        }
        log.info("Sorted by date: " + result);
        return result;
    }
}
