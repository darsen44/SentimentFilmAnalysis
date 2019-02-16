import java.util.Arrays;

public class Adaptor
{
    public static void main(String[] args) {
        String str = "epam";
        int index = 0;
        char[] last = new char[str.length()];
        char[] chars = str.toCharArray();
        String [] array =new String[chars.length];

        subLast(chars,array,str);
        Arrays.sort(array);
        findIndex(array,index,str);
        findLastSymbols(array,last);
    }

    private static void subLast(char[] chars,String[] array, String str){
        for (int i = 0; i < chars.length ; i++) {
            array[i] = str;
            char ch = str.charAt(0);
            str = str.substring(1);
            str = str + ch;
        }
        System.out.println(Arrays.asList(array));
    }

    private static void findIndex(String[] array, int index, String word) {
        System.out.println(Arrays.asList(array));
        for (int i = 0; i <array.length ; i++) {
            if(array[i].equals(word))
                index = i;
        }
        System.out.println(index);
    }
    private static void findLastSymbols(String[] array, char[] last) {
        for (int i = 0; i <array.length ; i++) {
            last[i] =array[i].charAt(array.length-1);
        }
        System.out.println(last);
    }




}
