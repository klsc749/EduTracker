package handler;
import model.Activity;
import view.Home;
import view.ModuleInfo;

import java.util.function.Consumer;

public class OnActivitySelectedHandler implements Consumer<Activity> {
    private final Home home;

    public OnActivitySelectedHandler(Home home) {
        this.home = home;
    }

    @Override
    public void accept(Activity activity) {
        if (activity.getType() == Activity.ActivityType.CLASS) {
            ModuleInfo moduleInfo = new ModuleInfo(activity);
            moduleInfo.setOnBackButtonClick(event -> {
                home.setRootRight(home.createScrollableActivityCardPaginationIfNotExit());
            });
            home.setRootRight(moduleInfo);
        } else if (activity.getType() == Activity.ActivityType.EXTRA) {
            //TODO ZPY open extra info
        }
    }
}