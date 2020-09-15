<!DOCTYPE html>
<html>
<head>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <title>
    Сервис заметок
  </title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script src="MainScript.js"></script>
</head>
<body style="text-align:center;" id="body">

   <p><input type="search" id="search" placeholder="Найти заметку"> 
   <input type="submit" value="Найти" onclick="searchNotes()"></p>

  <h1> Созадай новую заметку! </h1>
  <!-- делаем форму с полями ввода -->
  <p>
    <input type="text" id="heading" placeholder="Заголовок">
    <textarea type="text" id="note" placeholder="Заметка"></textarea>
    <!-- по нажатию на эту кнопку данные уйдут на сервер -->
    <button onclick="createNote()">Создать заметку</button>
  </p>
  
  <h1> Список заметок! </h1>
  <button onclick="showAllNotes()">Отобразить все заметки</button>
  <p class="result" style="color:blue"></p>
</body>
</html>