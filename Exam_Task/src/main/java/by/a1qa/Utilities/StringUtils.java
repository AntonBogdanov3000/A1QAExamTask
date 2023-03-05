package by.a1qa.Utilities;

public class StringUtils {

    public static String getRandomText(){

        Character[] characterList = new Character[]{'a','b','c','d','e','f','g','h','i','j','k',
                'l','m','n','o','p','q','r','s','t','u','v', 'w','x','y','z'};
        String text = "";
        int random = Integer.parseInt(FileReader.getTestData("randomSize", "userData.json"));
        StringBuffer textBuffer = new StringBuffer(text);

        for(int i=0; i<random; i++){
            text = textBuffer.append(characterList[(int)(Math.random()*(characterList.length-1))+1]).toString();
        }
        return text;
    }

    public static int getProjectId(String text){
        return text.indexOf("=")+1;
    }
}
