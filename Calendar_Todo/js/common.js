var currentTitle = document.getElementById('current-year-month');
var calendarBody = document.getElementById('calendar-body');
var today = new Date();
var first = new Date(today.getFullYear(), today.getMonth(), 1);
var dayList = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
var monthList = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October','November','December'];
var leapYear = [31,29,31,30,31,30,31,31,30,31,30,31];
var notLeapYear = [31,28,31,30,31,30,31,31,30,31,30,31];
var pageFirst = first;
var pageYear;
var mainTodayDay = document.getElementById('mainTodayDay');
var mainTodayDate = document.getElementById('mainTodayDate');
clickedDate1 = document.getElementById(today.getDate());

//localstorage 저장용 객체 만들기
var to_do = function(to_do_id, content) {
    this.to_do_id = to_do_id;
    this.content = content;
}

var to_do_list = [];

var todo = new to_do('id', 'todotxt');
console.log(todo);

//에러때문에 변수 옮김
var tdGroup = [];

var todoList = [];
var keyValue = today.getFullYear() + '' + today.getMonth() + '' + today.getDate();
todoList[keyValue] = [];


var inputBox = document.getElementById('input-box');
var inputDate = document.getElementById('input-data');
var inputList = document.getElementById('input-list');
var delText = 'X';
inputDate.addEventListener('click', addTodoList);
var dataCnt = 1;

var clickedDate1 = document.getElementById(today.getDate());

var preBtn = document.getElementById('prev');
var nextBtn = document.getElementById('next');


// 달력 출력하기

if(first.getFullYear() % 4 === 0){
    pageYear = leapYear;
}else{
    pageYear = notLeapYear;
}

function showCalendar() {
    let monthCnt = 100;
    let cnt = 1;
    let daycnt = first.getDay(); //#요일
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    for(var i = 0; i < 6; i++){
        var $tr = document.createElement('tr');
        $tr.setAttribute('id', monthCnt);
        for(var j = 0; j < 7; j++){
            if((i === 0 && j < first.getDay()) || cnt > pageYear[first.getMonth()]){
                var $td = document.createElement('td');
                $tr.appendChild($td);
            }else{
                var $td = document.createElement('td');
                $td.textContent = cnt;
                $td.setAttribute('id', cnt);
                $td.setAttribute('day', dayList[(daycnt % 7)]); //#요일
                $tr.appendChild($td);
                cnt++;
                daycnt++;
            }
        }
        monthCnt++;
        calendarBody.appendChild($tr);
    }
    showMain();
    clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');
    clickStart();
    reshowingList();
}

showCalendar(); // 이게 있어야 화면에 출력됨


function removeCalendar() {
    let catchTr = 100;
    for(let i = 100; i < 106; i++){
        var $tr = document.getElementById(catchTr);
        $tr.remove();
        catchTr++;
    }
}


// 이전 달과 다음 달로 이동하기

function prev() {
    inputBox.value = "";
    const $divs = document.querySelectorAll('#input-list > div');
    $divs.forEach(function(e) {
        e.remove();
    });
    const $btns = document.querySelectorAll('#input-list > button');
    $btns.forEach(function(e1) {
        e1.remove();
    });
    if(pageFirst.getMonth() === 1){
        pageFirst = new Date(first.getFullYear()-1, 12, 1);
        first = pageFirst;
        if(first.getFullYear() % 4 === 0){
            pageYear = leapYear;
        }else{
            pageYear = notLeapYear;
        }
    }else{
        pageFirst = new Date(first.getFullYear(), first.getMonth()-1, 1);
        first = pageFirst;
    }
    today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    removeCalendar();
    showCalendar();
    showMain();
    clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');
    clickStart();
    reshowingList();
}

function next() {
    inputBox.value = "";
    const $divs = document.querySelectorAll('#input-list > div');
    $divs.forEach(function (e) {
        e.remove();
    });
    const $btns = document.querySelectorAll('#input-list > button');
    $btns.forEach(function(e1) {
        e1.remove();
    });
    if(pageFirst.getMonth() === 12){
        pageFirst = new Date(first.getFullYear()+1, 1, 1);
        first = pageFirst;
        if(first.getFullYear() % 4 === 0){
            pageYear = leapYear;
        }else{
            pageYear = notLeapYear;
        }
    }else{
        pageFirst = new Date(first.getFullYear(), first.getMonth()+1, 1);
        first = pageFirst;
    }
    today = new Date(today.getFullYear(), first.getMonth()+1, today.getDate());
    currentTitle.innerHTML = monthList[first.getMonth()] + '&nbsp;&nbsp;&nbsp;&nbsp;'+ first.getFullYear();
    removeCalendar();
    showCalendar();
    showMain();
    clickedDate1 = document.getElementById(today.getDate());
    clickedDate1.classList.add('active');
    clickStart();
    reshowingList();
}

// 클릭해서 날짜 색상 변경 & 왼쪽 화면 변경

function showMain() {
    let dayEle = document.getElementById(today.getDate()); //#요일
    mainTodayDay.innerHTML = dayEle.getAttribute('day');
    mainTodayDate.innerHTML = today.getDate(); //#요일
}


clickedDate1.classList.add('active');

preBtn.addEventListener('click', prev);
nextBtn.addEventListener('click', next);


function clickStart() {


    for(let i = 1; i <= pageYear[first.getMonth()]; i++){
        tdGroup[i] = document.getElementById(i);
        tdGroup[i].addEventListener('click', changeToday);
    }
}

function changeToday(e) {
    document.getElementById(today.getDate()).classList.remove('active');
    for(let i = 1; i < pageYear[first.getMonth()]; i++){
        if(tdGroup[i].classList.contains('active')){
            tdGroup[i].classList.remove('active');
        }
    }
    clickedDate1 = e.currentTarget;
    clickedDate1.classList.add('active');
    today = new Date(today.getFullYear(), today.getMonth(), clickedDate1.id);
    showMain();
    keyValue = today.getFullYear() + '' + today.getMonth() + '' + today.getDate();
    reshowingList();
}

//Todo-List 입력, 체크, 삭제하기

function reshowingList() {
    keyValue = today.getFullYear() + '' + today.getMonth() + '' + today.getDate();
    if(todoList[keyValue] === undefined){
        inputList.textContent = '';
        todoList[keyValue] = [];
        const $divs = document.querySelectorAll('#input-list > div');
        $divs.forEach(function (e) {
            e.remove();
        });
        const $btns = document.querySelectorAll('#input-list > button');
        $btns.forEach(function (e1) {
            e1.remove();
        });
    }else if(todoList[keyValue].length === 0){
        inputList.textContent = "";
        const $divs = document.querySelectorAll('#input-list > div');
        $divs.forEach(function (e) {
            e.remove();
        });
        const $btns = document.querySelectorAll('#input-list > button');
        $btns.forEach(function (e1) {
            e1.remove();
        });
    }else{
        const $divs = document.querySelectorAll('#input-list > div');
        $divs.forEach(function (e) {
            e.remove();
        });
        const $btns = document.querySelectorAll('#input-list > button');
        $btns.forEach(function (e1) {
            e1.remove();
        });
        var $div = document.createElement('div');
        for(var i = 0; i < todoList[keyValue].length; i++){
            var $div = document.createElement('div');
            $div.textContent = '- ' + todoList[keyValue][i];
            $div.setAttribute('id', 'todotxt'+dataCnt+keyValue);
            var $btn = document.createElement('button');
            $btn.setAttribute('type', 'button');
            $btn.setAttribute('id', 'del-ata');
            $btn.setAttribute('id', dataCnt+keyValue);
            $btn.setAttribute('class', 'del-data');
            $btn.textContent = delText;
            inputList.appendChild($div);
            inputList.appendChild($btn);
            $div.addEventListener('click', checkList);
            $btn.addEventListener('click', deleteTodo);
            inputBox.value = '';
            
            function deleteTodo() {

                let len = todoList[keyValue].length;
                for(let j = 0; j < len; j++){
                    if(('- '+todoList[keyValue][j]) == $div.textContent){
                        todoList[keyValue].splice(j,1);
                        $div.remove();
                        $btn.remove();
                        break;
                    }
                } 
            }
        }
    }
}




function addTodoList() {
    var $div = document.createElement('div');
    $div.textContent = '- ' + inputBox.value;
    $div.setAttribute('id', 'todotxt'+dataCnt+keyValue);
    to_do_list.push(new to_do('todotxt'+dataCnt+keyValue, inputBox.value))
    var $btn = document.createElement('button');
    $btn.setAttribute('type', 'button');
    $btn.setAttribute('id', 'del-data');
    $btn.setAttribute('id', dataCnt+keyValue);
    $btn.setAttribute('class', "del-data");
    $btn.textContent = delText;
    inputList.appendChild($div);
    inputList.appendChild($btn);
    todoList[keyValue].push(inputBox.value);
    dataCnt++;
    console.log("after dataCnt++ : " + dataCnt);
    inputBox.value = '';
    $div.addEventListener('click', checkList);
    $btn.addEventListener('click', deleteTodo);

    function deleteTodo() {

        let len = todoList[keyValue].length;
        for(var i = 0; i < len; i++){
            if(('- '+todoList[keyValue][i]) == $div.textContent){
                to_do_list.splice(i,1);
                todoList[keyValue].splice(i,1);
                $div.remove();
                $btn.remove();
                console.log("after remove : ");
                console.log(to_do_list);
                localStorage.setItem(today.getDate(), JSON.stringify(to_do_list));
                break;
            }
        } 
    }
    
    localStorage.setItem(today.getDate(), JSON.stringify(to_do_list));
    console.log(to_do_list);

}


function checkList(e) {
    e.currentTarget.classList.add('checked');
}

