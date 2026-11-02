package hung.edu.apptienganh.engine.registry;

public class AppModule {
    public String code;
    public String titleVi;
    public String descriptionVi;
    public AppModuleCategory category;
    public Class<?> targetActivityClassName;
    public int iconResId;
    public boolean isDebugOnly;
    public boolean isAvailable;
    public String requiredLevel;
    public int orderIndex;

    public AppModule(String code, String titleVi, String descriptionVi, AppModuleCategory category, Class<?> targetActivityClassName) {
        this.code = code;
        this.titleVi = titleVi;
        this.descriptionVi = descriptionVi;
        this.category = category;
        this.targetActivityClassName = targetActivityClassName;
        this.isAvailable = true;
    }
}
