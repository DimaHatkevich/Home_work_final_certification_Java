import java.util.*;

class Laptop {
    private String os;      // Операционная система
    private int ram;        // ОЗУ в ГБ
    private int hdd;        // Объем ЖД в ГБ
    private String color;    // Цвет

    public Laptop(String os, int ram, int hdd, String color) {
        this.os = os;
        this.ram = ram;
        this.hdd = hdd;
        this.color = color;
    }

    public String getOs() {
        return os;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{OS='" + os + "', RAM=" + ram + "GB, HDD=" + hdd + "GB, Color='" + color + "'}";
    }
}

class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        laptops = new HashSet<>();
        // Добавление примеров ноутбуков
        laptops.add(new Laptop("Windows", 8, 512, "Black"));
        laptops.add(new Laptop("Linux", 16, 1024, "Silver"));
        laptops.add(new Laptop("macOS", 8, 256, "Gray"));
        // Добавьте больше ноутбуков по желанию
    }

    public void filterLaptops() {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> criteria = new HashMap<>();
        int choice;

        do {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ (в ГБ)");
            System.out.println("2 - Объем ЖД (в ГБ)");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("0 - Завершить ввод критериев");

            choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            if (choice == 1) {
                System.out.print("Введите минимальный объем ОЗУ: ");
                criteria.put(1, scanner.nextInt());
            } else if (choice == 2) {
                System.out.print("Введите минимальный объем ЖД: ");
                criteria.put(2, scanner.nextInt());
            } else if (choice == 3) {
                System.out.print("Введите операционную систему: ");
                criteria.put(3, scanner.nextLine());
            } else if (choice == 4) {
                System.out.print("Введите цвет: ");
                criteria.put(4, scanner.nextLine());
            }

        } while (choice != 0);

        // Фильтрация ноутбуков
        Set<Laptop> filteredLaptops = filter(criteria);

        // Вывод результатов
        if (filteredLaptops.isEmpty()) {
            System.out.println("Ноутбуки не найдены по указанным критериям.");
        } else {
            System.out.println("Найденные ноутбуки:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }

    private Set<Laptop> filter(Map<Integer, Object> criteria) {
        Set<Laptop> filtered = new HashSet<>(laptops);

        for (Map.Entry<Integer, Object> entry : criteria.entrySet()) {
            switch (entry.getKey()) {
                case 1:
                    int minRam = (Integer) entry.getValue();
                    filtered.removeIf(lap -> lap.getRam() < minRam);
                    break;
                case 2:
                    int minHdd = (Integer) entry.getValue();
                    filtered.removeIf(lap -> lap.getHdd() < minHdd);
                    break;
                case 3:
                    String os = (String) entry.getValue();
                    filtered.removeIf(lap -> !lap.getOs().equalsIgnoreCase(os));
                    break;
                case 4:
                    String color = (String) entry.getValue();
                    filtered.removeIf(lap -> !lap.getColor().equalsIgnoreCase(color));
                    break;
            }
        }

        return filtered;
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.filterLaptops();
    }
}

