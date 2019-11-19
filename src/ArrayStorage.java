import static java.util.stream.IntStream.range;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        range(0, storage.length).forEach(i -> storage[i] = null);
    }

    void save(Resume r) {
        range(0, storage.length).filter(i -> storage[i] == null).findFirst().ifPresent(i -> storage[i] = r);
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) break;
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) break;
            if (storage[i].uuid == uuid) index = i;
        }
        if (index == -1) {
            System.out.println("Resume not found");
            return;
        }
        if (storage[index + 1] != null) {
            storage[index] = storage[index + 1];
            storage[index + 1] = null;
            return;
        }
        storage[index] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int len = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) break;
            len++;
        }
        Resume[] resumes = new Resume[len];
        System.arraycopy(storage, 0, resumes, 0, len);
        return resumes;
    }

    int size() {
        return storage.length;
    }
}
