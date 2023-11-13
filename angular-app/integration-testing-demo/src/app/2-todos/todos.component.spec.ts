/* tslint:disable:no-unused-variable */
import {
  async,
  ComponentFixture,
  fakeAsync,
  TestBed,
  tick,
} from '@angular/core/testing';

import { TodosComponent } from './todos.component';
import { TodoService } from './todo.service';
import { HttpClientModule } from '@angular/common/http';
import { Observable, of } from 'rxjs';

//NOTE: I've deliberately excluded this suite from running
// because the test will fail. This is because we have not
// provided the TodoService as a dependency to TodosComponent.
//
// When you get to Lecture 6 (Providing Dependencies), be sure
// to remove "x" from "xdescribe" below.

describe('TodosComponent', () => {
  let component: TodosComponent;
  let fixture: ComponentFixture<TodosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [TodosComponent],
      providers: [TodoService],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodosComponent);
    component = fixture.componentInstance;
  });

  it('should load todos from the server', () => {
    let service = TestBed.get(TodoService);
    // let service = fixture.debugElement.injector.get(TodoService); too noisy so the above is good compared to this
    spyOn(service, 'getTodos').and.returnValue(of([1, 2, 3]));
    fixture.detectChanges();
    expect(component.todos.length).toBe(3); //assertions
    console.log('Observable Expect was called');
  });

  it('should load todos from the server as promise', async () => {
    let service = TestBed.get(TodoService);
    spyOn(service, 'getTodosPromise').and.returnValue(
      Promise.resolve([1, 2, 3])
    );

    fixture.detectChanges();
    // tick();
    fixture.whenStable().then(() => {
      expect(component.todos.length).toBe(3); // delay this part of line until the async operations in our component are completed
    });
    console.log('Promise Expect was called');
  });
});
