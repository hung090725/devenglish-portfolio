package hung.edu.apptienganh.data.local.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "minimal_pair_exercises")
public class MinimalPairExercise {
    @PrimaryKey(autoGenerate = true) public int id;
    public int drillId;
    public String word1;
    public String word2;
    public String sound1;
    public String sound2;
    public MinimalPairExercise() {}
}
