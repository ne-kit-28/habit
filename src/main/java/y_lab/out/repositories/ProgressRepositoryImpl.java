package y_lab.out.repositories;

import lombok.Getter;
import lombok.Setter;
import y_lab.domain.entities.Habit;
import y_lab.domain.entities.Progress;
import y_lab.domain.entities.User;
import y_lab.domain.repositories.ProgressRepository;
import y_lab.service.DataService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Getter
@Setter
public class ProgressRepositoryImpl implements ProgressRepository, DataService {
    HashMap<Long, Progress> progresses = new HashMap<>();
    Long idGenerated = 0L;

    public ProgressRepositoryImpl(String fileName) {
        this.loadFromFile(fileName);
    }

    /**
     * @param progress
     */
    @Override
    public void save(Progress progress) {
        progress.setId(idGenerated);
        progresses.put(idGenerated, progress);
        ++idGenerated;
    }

    /**
     * @param habitId
     */
    @Override
    public void deleteAllByHabitId(Long habitId) {
        for(Map.Entry<Long, Progress> entry : progresses.entrySet()) {
            if (entry.getValue().getHabit().getId().equals(habitId))
                progresses.remove(entry.getKey());
        }
    }

    /**
     * @param userId
     */
    @Override
    public void deleteAllByUserId(Long userId) {
        for(Map.Entry<Long, Progress> entry : progresses.entrySet()) {
            if (entry.getValue().getUser().getId().equals(userId))
                progresses.remove(entry.getKey());
        }
    }

    /**
     * @param progressId
     * @return
     */
    @Override
    public Optional<Progress> findById(Long progressId) {
        return Optional.of(progresses.get(progressId));
    }

    /**
     * @param habitId
     * @return
     */
    @Override
    public ArrayList<Progress> findByHabitId(Long habitId) {
        ArrayList<Progress> arrayList = new ArrayList<>();
        for(Map.Entry<Long, Progress> entry : progresses.entrySet()) {
            if (entry.getValue().getHabit().getId().equals(habitId))
                arrayList.add(entry.getValue());
        }
        return arrayList;
    }

    @Override
    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this.getProgresses());
            out.writeObject(this.getIdGenerated());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.setProgresses((HashMap<Long, Progress>) in.readObject());
            this.setIdGenerated((Long) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
