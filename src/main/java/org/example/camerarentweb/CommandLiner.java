package org.example.camerarentweb;

import org.example.camerarentweb.entities.*;
import org.example.camerarentweb.repositories.*;
import org.example.camerarentweb.repositories.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CommandLiner implements CommandLineRunner {
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EquipmentTypeRepository equipmentTypeRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private EquipmentUnitRepository equipmentUnitRepository;

    private Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        //пользователи
        User user1 = new User(
                "+78381223223",
                "password",
                "some@mad.ru",
                "Mad",
                "Pole",
                UserRole.USER
                );
        user1 = userRepository.save(user1);

        User user2 = new User(
                "+79991223777",
                "myownsecretpassword",
                "dimaa77777@hotmail.com",
                "G",
                "Dima",
                UserRole.USER
        );

        user2 = userRepository.save(user2);

        User user3 = new User(
                "89090073399",
                "toopseeecreet))",
                "somemaaail@mail.ru",
                "Belfort",
                "Jordan",
                UserRole.USER
        );
        user3 = userRepository.save(user3);



        // категории (надо подумать как названия норм сделать мб поле добавить в энтити)
        Category cameras = new Category();
        cameras.setName("cameras");
        cameras.setParameters("Разрешение, Тип сенсора");
        cameras = categoryRepository.save(cameras);

        Category lenses = new Category();
        lenses.setName("lenses");
        lenses.setParameters("Тип объектива");
        lenses = categoryRepository.save(lenses);

        Category lights = new Category();
        lights.setName("lights");
        lights.setParameters("Мощность, Цветовая температура");
        lights = categoryRepository.save(lights);

        Category operatorEquipments = new Category();
        operatorEquipments.setName("operatorEquipments");
        operatorEquipments = categoryRepository.save(operatorEquipments);

        Category readyKits = new Category();
        readyKits.setName("readyKits");
        readyKits = categoryRepository.save(readyKits);

        Category accessories = new Category();
        accessories.setName("accessories");
        accessories = categoryRepository.save(accessories);

        Category externalDevices = new Category();
        externalDevices.setName("externalDevices");
        externalDevices = categoryRepository.save(externalDevices);

        // типы оборудования
        var equipmentType14 = createEquipmentType("Камера Canon EOS R5",
                "Полнокадровая камера с разрешением 45 МП. "+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer egestas, nulla eu viverra suscipit, augue velit convallis nisi, vitae scelerisque diam dui ut orci. Nullam et pulvinar nibh. ",
                3000.0,
                cameras,
                "canon_r5.jpg");
        var equipmentType1 = createEquipmentType("Камера Sony FX3",
                "Кинокамера с 4K 120fps",
                4000.0,
                cameras,
                "sony_fx3.jpg");
        var equipmentType2 = createEquipmentType("Объектив Canon RF 24-70mm f2.8",
                "Универсальный зум-объектив",
                1000.0,
                lenses,
                "canon_rf24-70.jpg");
        var equipmentType3 = createEquipmentType("Объектив Sony FE 50mm f1.2",
                "Портретный объектив с высокой светосилой",
                1500.0,
                lenses,
                "sony_fe50mm.jpg");
        var equipmentType4 = createEquipmentType("LED свет Aputure 120D II",
                "Мощный LED свет для видеосъемки",
                500.0,
                lights,
                "aputure_120d.jpg");
        var equipmentType5 = createEquipmentType(
                "Световая панель Godox SL60W",
                "Компактный LED свет",
                200.0,
                lights,
                "godox_sl60w.jpg");
        var equipmentType6 = createEquipmentType(
                "Стабилизатор DJI Ronin-S",
                "Одноручный стабилизатор для камер",
                800.0,
                operatorEquipments,
                "dji_ronin_s.jpg");
        var equipmentType7 = createEquipmentType(
                "Слайдер Neewer 80cm",
                "Рельсовый слайдер для съемки",
                200.0,
                operatorEquipments,
                "neewer_slider.jpg");
        var equipmentType8 = createEquipmentType(
                "Комплект для стрима",
                "Камера Canon R5 + свет + аксессуары",
                6000.0,
                readyKits,
                "canon_r5_kit.jpg");
        var equipmentType9 = createEquipmentType(
                "Комплект для интервью",
                "Камера Canon EOS R6 + обьектив Canon RF 24-70 f2.8 L IS USM + свет + микрофон",
                3500.0,
                readyKits,
                "interview_kit.jpg");
        var equipmentType10 = createEquipmentType(
                "Крепление SmallRig 2935",
                "Клетка для камеры",
                150.0,
                accessories,
                "smallrig_cage.jpg");
        var equipmentType11 = createEquipmentType(
                "Штатив Manfrotto Befree",
                "Компактный штатив для путешествий",
                250.0,
                accessories,
                "manfrotto_befree.jpg");
        var equipmentType12 = createEquipmentType(
                "Монитор Atomos Ninja V",
                "Записывающий монитор с 4K HDR",
                700.0,
                externalDevices,
                "atomos_ninja_v.jpg");
        var equipmentType13 = createEquipmentType(
                "Рекордер Zoom H6",
                "Многоканальный рекордер для звука",
                400.0,
                externalDevices,
                "zoom_h6.jpg");

        // отзывы
        List<User> users = List.of(user1,user2);
        List<EquipmentType> equipmentTypes = List.of(equipmentType1,
                equipmentType2,equipmentType3,equipmentType4,equipmentType5,
                equipmentType6, equipmentType7, equipmentType8, equipmentType9,
                equipmentType10, equipmentType11, equipmentType12, equipmentType13,
                equipmentType14);

        //List<EquipmentType> equipmentTypes = equipmentTypeRepository.findAll(1,99);

        createReview(users.get(0), equipmentTypes.get(0), 9, "Отличная камера!",
                "Высокое качество изображения, удобное управление",
                "Высокая цена",
                "Sony FX3 - это экстра классная камера, которая супер сильно подходит " +
                        "для профессиональной съемки.");

        createReview(users.get(1), equipmentTypes.get(0), 3, "Норм камера",
                "Высокое качество, управление на любителя",
                "Безумно дорого",
                "Не для всех увы...");

        createReview(users.get(1), equipmentTypes.get(2), 8, "Универсальный объектив",
                "Широкий диапазон фокусных расстояний, отличное качество изображения",
                "Немного тяжеловат",
                "Canon RF 24-70mm f2.8 - отличный выбор для разнообразной съемки.");

        createReview(users.get(0), equipmentTypes.get(4), 7, "Яркий и мощный свет",
                "Высокая мощность, регулируемая цветовая температура",
                "Нужен дополнительный аккумулятор для длительной работы",
                "Aputure 120D II обеспечивает качественное освещение для видеосъемки.");

        for (int i = 0; i < equipmentTypes.size(); i++) {
            User randomUser = users.get(random.nextInt(users.size()));
            EquipmentType equipmentType = equipmentTypes.get(i);
            int rate = random.nextInt(7) + 4;
            createRandomReview(randomUser, equipmentType, rate);
        }
        for (int i = 0; i < equipmentTypes.size(); i++) {
            User randomUser = users.get(random.nextInt(users.size()));
            EquipmentType equipmentType = equipmentTypes.get(i);
            int rate = random.nextInt(8) + 3;
            createRandomReview(randomUser, equipmentType, rate);
        }


        // экземпляры обордования

        EquipmentUnit equipmentUnit1 = new EquipmentUnit();
        equipmentUnit1.setEquipmentType(equipmentType1);
        equipmentUnit1.setSerialNumber("AAAA-AAAA-AAAA");
        equipmentUnit1.setStatus(EquipmentStatus.AVAILABLE);
        equipmentUnit1.setNotes("царапины");
        equipmentUnitRepository.save(equipmentUnit1);

        EquipmentUnit equipmentUnit2 = new EquipmentUnit();
        equipmentUnit2.setEquipmentType(equipmentType2);
        equipmentUnit2.setSerialNumber("BBBB-CCCC-65SS");
        equipmentUnit2.setStatus(EquipmentStatus.AVAILABLE);
        equipmentUnit2.setNotes("батарея перегревается");
        equipmentUnitRepository.save(equipmentUnit2);


        // заказы


    }

    private EquipmentType createEquipmentType(String name, String description, double price, Category category, String photo) {
        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setName(name);
        equipmentType.setDescription(description);
        equipmentType.setPrice(price);
        equipmentType.setCategory(category);
        equipmentType.setPhoto(photo);
        return equipmentTypeRepository.save(equipmentType);
    }

    private void createReview(User user, EquipmentType equipmentType, int rate, String header,
                              String advantages, String disadvantages, String commentary) {
        Review review = new Review();
        review.setUser(user);
        review.setEquipmentType(equipmentType);
        review.setRate(rate);
        review.setHeader(header);
        review.setAdvantages(advantages);
        review.setDisadvantages(disadvantages);
        review.setCommentary(commentary);
        reviewRepository.save(review);
    }

    private void createRandomReview(User user, EquipmentType equipmentType, int rate) {
        String header = "Отзыв о " + equipmentType.getName();
        String advantages = "Хорошее качество, удобство использования";
        String disadvantages = "Нет существенных недостатков";
        String commentary = "Оборудование " + equipmentType.getName() + " оправдало ожидания. Рекомендую!";

        createReview(user, equipmentType, rate, header, advantages, disadvantages, commentary);
    }
}
