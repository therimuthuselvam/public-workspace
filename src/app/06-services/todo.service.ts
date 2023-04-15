
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

export class TodoService {

  private http: HttpClient | any; // adding it in constructor making problem in test cases

  constructor() {
  }

  add(todo: { title: string; }) {
    return this.http.post('...', todo)//.pipe(map(r => r));
  }

  getTodos() {
    return this.http.get('...');//.map(r => r.json());
  }

  delete(id: any) {
    return this.http.delete('...');//.map(r => r.json());
  }
}
