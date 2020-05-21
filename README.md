# WidgetProject
Anroid, Java, Widget

This project is focused to implement widgets in our application. Widgets make a connection to the user with the app when the app is not in the foreground.

Purpose of this project is to focus on two basic commands:

1- Users can trigger right from the home screen without having to open the app first. This commend is executed and send back the feedback.
In to the app user click example TextView and a toast will be shown. All this is handled in the background without opening the app and to make this works is used BroadcastReceiver.

2- Users can trigger right from the home screen but now the app will open at a specific activity with some data transferred based on the item clicked into the ListView inside the widget.
