import static java.util.stream.IntStream.range;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private int size;

    void clear() {
        range(0, size).forEach(i -> storage[i] = null);
        size = 0;
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            storage[size - 1] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int siz = size;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                for (int j = i; j <= size; j++) {
                    storage[j] = storage[j + 1];
                }
                size--;
                break;
            }
        }
        if (siz == size) {
            System.out.println("Resume item with uuid " + uuid + " not found");
            return;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    int size() {
        return size;
    }
}
