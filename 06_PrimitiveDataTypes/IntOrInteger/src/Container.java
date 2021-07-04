public class Container {
    private Integer count; // count, ссылка на объект, без инициализации - null, поэтому unboxing невоможен был

    public Container(Integer count) { // добавил конструктор для инициалиации count
        this.count = count;
    }

    public void addCount(int value) {
        count = count + value;
    }

    public int getCount() {
        return count;
    }
}
