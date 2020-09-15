function createNote() {
	var xhr = new XMLHttpRequest();
	
	var heading = document.getElementById('heading').value;
	var note = document.getElementById('note').value;
	
	var body = "/NaumenTestTask/MainServlet?command=create&heading=" + heading +
  	"&note=" + note;

	xhr.open("POST", body, true);

	xhr.onreadystatechange = function () {
      // если запрос принят и сервер ответил, что всё в порядке
      if (xhr.readyState === 4 && xhr.status === 200) {
    	document.querySelector('.result').innerHTML = this.responseText;
	  }
    };

	xhr.send(body);
}

function showAllNotes() {
	var xhr = new XMLHttpRequest();
	
	var body = "/NaumenTestTask/MainServlet?command=showAll";

	xhr.open("POST", body, true);

	xhr.onreadystatechange = function () {
      // если запрос принят и сервер ответил, что всё в порядке
      if (xhr.readyState === 4 && xhr.status === 200) {
    	document.querySelector('.result').innerHTML = this.responseText;
	  }
    };

	xhr.send(body);
}

function deleteNote(id) {
	var xhr = new XMLHttpRequest();
		
	var body = "/NaumenTestTask/MainServlet?command=delete&id=" + id;

	xhr.open("POST", body, true);

	xhr.onreadystatechange = function () {
      // если запрос принят и сервер ответил, что всё в порядке
      if (xhr.readyState === 4 && xhr.status === 200) {
    	document.querySelector('.result').innerHTML = this.responseText;
	  }
    };

	xhr.send(body);
}

function searchNotes() {
	var xhr = new XMLHttpRequest();
	
	var keyWord = document.getElementById('search').value;
	
	var body = "/NaumenTestTask/MainServlet?command=search&keyWord=" + keyWord;

	xhr.open("POST", body, true);

	xhr.onreadystatechange = function () {
      // если запрос принят и сервер ответил, что всё в порядке
      if (xhr.readyState === 4 && xhr.status === 200) {
    	document.querySelector('.result').innerHTML = this.responseText;
	  }
    };

	xhr.send(body);
}

function showNote(id) {
	var xhr = new XMLHttpRequest();
	
	var body = "/NaumenTestTask/MainServlet?command=showNote&id=" + id;

	xhr.open("POST", body, true);

	xhr.onreadystatechange = function () {
      // если запрос принят и сервер ответил, что всё в порядке
      if (xhr.readyState === 4 && xhr.status === 200) {
    	document.querySelector('.result').innerHTML = this.responseText;
	  }
    };

	xhr.send(body);
}