import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../list-todos/list-todos.component';
import { TodoDataService } from '../service/data/todo-data.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css'],
})
export class TodoComponent implements OnInit {
  todo!: Todo;
  username = 'in28minutes';
  id!: number;

  constructor(
    private todoService: TodoDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.todo = new Todo(this.id, '', new Date(), false);
    if (this.id != -1) {
      this.todoService
        .retrieveTodo(this.username, this.id)
        .subscribe((data) => {
          this.todo = data;
        });
    }
  }

  saveTodo() {
    if (this.id == -1) {
      console.log('save');
      this.todoService.createTodo('in28minutes', this.todo).subscribe(
        data => {
          this.router.navigate(['/todos'])
        }
      )
    } else {
      this.todoService
        .updateTodo(this.username, this.id, this.todo)
        .subscribe((data) => {
          this.router.navigate(['/todos']);
        });
    }
  }
}
