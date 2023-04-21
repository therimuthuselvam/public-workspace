import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {

  todos: any
  message: string = ''

  // [
  //   new Todo(1, 'Learn to Dance', false, new Date()),
  //   new Todo(2, 'Become an expert at Angular', false, new Date()),
  //   new Todo(3, 'Visit India', false, new Date())
  // ]

  /*todo = {
    id : 1,
    description: 'Learn to Dance'
  }*/

  constructor(
    private todoService: TodoDataService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.refreshTodos()
  }

  refreshTodos() {
    this.todoService.retriveAllTodos('in28minutes').subscribe(
      response => {
        this.todos = response
      },
      error => {
        console.log(error)
      }
    )
  }

  deleteTodo(id: number) {
    this.todoService.deleteTodo('in28minutes', id).subscribe(
      response => {
        this.message = `Delete of Todo id ${id} is Successful`
        this.refreshTodos()
      }
    )
  }

  updateTodo(id: number) {
    this.router.navigate(['todos', id])
  }

  addTodo() {
    this.router.navigate(['todos', -1])
  }

}

export class Todo {
  constructor(
    public id: number,
    //public username:string,
    public description: string,
    public targetDate: Date,
    public done: boolean

  ) { }

}
