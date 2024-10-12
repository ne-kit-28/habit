package y_lab.out.repositories;

import lombok.Getter;
import lombok.Setter;
import y_lab.domain.entities.Habit;
import y_lab.domain.entities.User;
import y_lab.domain.repositories.HabitRepository;
import y_lab.service.DataService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Setter
@Getter
public class HabitRepositoryImpl implements HabitRepository, DataService {
    HashMap<Long, Habit> habits = new HashMap<>();
    Long idGenerated = 0L;

    public HabitRepositoryImpl(String fileName) {
        this.loadFromFile(fileName);
    }
    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Habit> findById(Long id) {
        return Optional.of(habits.get(id));
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Optional<Habit> findByName(String name, Long userId) {
        for (Map.Entry<Long, Habit> entry : habits.entrySet()) {
            if (entry.getValue().getName().equals(name) && entry.getValue().getUser().getId().equals(userId))
                return Optional.of(entry.getValue());
        }
        return Optional.empty();
    }

    /**
     * @param habit
     */
    @Override
    public void save(Habit habit) {
        habit.setId(idGenerated);
        habits.put(idGenerated, habit);
        ++idGenerated;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        habits.remove(id);
    }

    /**
     * @param userId
     */
    @Override
    public void deleteAllByUserId(Long userId) {
        // Создаем список для хранения ключей, которые нужно удалить
        ArrayList<Long> keysToDelete = new ArrayList<>();

        // Сначала собираем ключи для удаления
        for (Map.Entry<Long, Habit> entry : habits.entrySet()) {
            if (entry.getValue().getUser().getId().equals(userId)) {
                keysToDelete.add(entry.getKey());
            }
        }

        for (Long key : keysToDelete) {
            this.delete(key);
        }
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public Optional<ArrayList<Habit>> findHabitsByUserId(Long userId) {
        ArrayList<Habit> habitArrayList = new ArrayList<>();
        for (Map.Entry<Long, Habit> entry : habits.entrySet()) {
            if (entry.getValue().getUser().getId().equals(userId))
                habitArrayList.add(entry.getValue());
        }
        return Optional.of(habitArrayList);
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Habit> getAll() {
        ArrayList<Habit> habitsArray;
        habitsArray = new ArrayList<>(habits.values());
        return habitsArray;
    }

    @Override
    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this.getHabits());
            out.writeObject(this.getIdGenerated());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.setHabits((HashMap<Long, Habit>) in.readObject());
            this.setIdGenerated((Long) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
