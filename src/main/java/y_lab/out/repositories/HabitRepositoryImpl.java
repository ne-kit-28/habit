package y_lab.out.repositories;

import y_lab.domain.entities.Habit;
import y_lab.domain.repositories.HabitRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HabitRepositoryImpl implements HabitRepository {
    HashMap<Long, Habit> habits = new HashMap<>();
    Long idGenerated = 0L;
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
}
