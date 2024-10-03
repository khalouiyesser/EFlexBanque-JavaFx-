package utils;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class TrayNotificationAlert {

    public static void notif(String title, String msg, NotificationType notifType, AnimationType notifAnimation,
            Duration notifDuration) {
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(msg);
        tray.setNotificationType(notifType);
        tray.setAnimationType(notifAnimation);
        tray.showAndDismiss(notifDuration);
    }
}
