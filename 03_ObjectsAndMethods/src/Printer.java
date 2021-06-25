public class Printer {

    private String queue = "";
    private int pagesCount = 0;
    private int documentsCount = 0;

    private static int printedPagesCount = 0;
    private static int printedDocumentsCount = 0;

    public void append(String text) {
        append(text, "Untitled", 1);
    }

    public void append(String text, String docName) {
        append(text, docName, 1);
    }

    public void append(String text, String docName, int pages) {
        pagesCount += pages;
        documentsCount++;
        queue += docName + " - " + text + " | " + " pages count: " + pages + "\n";
    }

    public void clear() {
        queue = "";
        pagesCount = 0;
        documentsCount = 0;
    }

    public void print() {
        System.out.print(queue);
        System.out.println("Print completed" + "\n");
        printedPagesCount += pagesCount;
        printedDocumentsCount += documentsCount;
        clear();
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getDocumentsCount() {
        return documentsCount;
    }

    public void totalPrintedPagesAndDocs() {
        System.out.println("Printed documents: " + printedDocumentsCount + "\n" +
                           "Printed pages: " + printedPagesCount + "\n");
    }

}
