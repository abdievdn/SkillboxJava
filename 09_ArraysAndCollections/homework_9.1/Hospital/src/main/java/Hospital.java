import java.util.Locale;

public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {

        //TODO: напишите метод генерации массива температур пациентов
        float[] patientsTemperatures = new float[patientsCount];
        for (int i = 0; i < patientsTemperatures.length; i++) {
            patientsTemperatures[i] = (float) (Math.random() * 8) + 32;
        }
        return patientsTemperatures;
    }

    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */
        int healthyPatientsCount = 0;
        float temperaturesSum = 0;
        String patientsTemperatures = "";

        for (float element : temperatureData) {
            patientsTemperatures += String.format(Locale.US, "%.1f", element) + " ";
            temperaturesSum += element;
            if (element >= 36.2F && element <= 36.9F)
                healthyPatientsCount++;
        }
        String report =
                "Температуры пациентов: " + patientsTemperatures.trim() +
                        "\nСредняя температура: " + String.format(Locale.US, "%.2f", (temperaturesSum / temperatureData.length)) +
                        "\nКоличество здоровых: " + healthyPatientsCount;

        return report;
    }
}
