import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable()
export class TodoService {
  constructor(private http: HttpClient) {}

  add(todo) {
    return this.http.post('...', todo); //.pipe(map((r) => r.json()));
  }

  getTodos() {
    return this.http.get('...'); //.pipe(map((r) => r.json()));
  }

  getTodosPromise() {
    return this.http.get('...').toPromise(); //.pipe(map((r) => r.json()).toPromise());
  }

  delete(id) {
    return this.http.delete('...'); //.map((r) => r.json());
  }
}
