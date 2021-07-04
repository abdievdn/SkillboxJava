public class Main {
    public static void main(String[] args) {
        Container container = new Container(0);
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.


        charsCodes('А', 'Я');
        charsCodes('Ё', 'Ё');
        charsCodes('а', 'я');
        charsCodes('ё', 'ё');
        charsCodes('\uFFFF', '\uFFFF');

    }

    private static void charsCodes(char a, char b) {
        for(int i = 0; i < 65536; i++) {
            if((char) i == a) {
                while((char) i != (char) ((int) b + 1)) {
                    System.out.println(i + " = " + (char) i);
                    i++;
                }
                return;
            }
        }
    }
}
