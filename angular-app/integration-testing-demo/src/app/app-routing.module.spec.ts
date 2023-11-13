import { routes } from './app-routing.module';
import { UsersComponent } from './users/users.component';

describe('routes', () => {
  it('should contain a route for /users', () => {
    expect(routes).toContain({ path: 'users', component: UsersComponent });
  });
});
