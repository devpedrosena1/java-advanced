package br.com.fiap.Twotdsq.jadv.aula01;

public class Dog {

    private String name;
    private Integer age;
    private String color;

    public Dog(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer newAge) {
        if (this.age > newAge) {
            throw new IllegalArgumentException("Age must not be like");
        }
        this.age = newAge;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    //    public Dog() {}
}
