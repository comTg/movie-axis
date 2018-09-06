package top.vetoer;

public class MethodTest {
    public static void main(String[] args){
        String episode = "22-";
        String start = episode.substring(0,episode.indexOf("-"));
        String end = episode.substring(episode.indexOf("-")+1);
        System.out.println(start);
        System.out.println(end);
    }
}
