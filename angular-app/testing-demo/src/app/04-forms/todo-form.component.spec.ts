import { FormBuilder } from '@angular/forms';
import { TodoFormComponent } from './todo-form.component';

describe('TodoFormComponent', () => {
  let component: TodoFormComponent;

  beforeEach(() => {
    component = new TodoFormComponent(new FormBuilder());

  });

  it('should create form with two controls', () => {
    expect(component.form.contains('name')).toBeTruthy(); // can use toBeTrue() also
    expect(component.form.contains('email')).toBeTruthy();
  });

  it('should make the form control(name) required', () => {
    let control = component.form.get('name');
    control?.setValue("")
    expect(control?.valid).toBeFalsy();
  });
});
