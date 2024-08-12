<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>Booking Page 1</title>
</head>
<body>
<main>
    <p>Hello Booking, page 1!</p>
    <a href="${flowExecutionUrl}&_eventId_success" >Trigger Success flow</a>
    <a href="${flowExecutionUrl}&_eventId_cancel" >Trigger Success flow</a>

    <form action="${flowExecutionUrl}" method="POST">
        <input type="submit" name="_eventId_success" value="Trigger Success flow" />
        <input type="submit" name="_eventId_cancel" value="Trigger Cancel flow" />
    </form>
</main>
</body>
</html>