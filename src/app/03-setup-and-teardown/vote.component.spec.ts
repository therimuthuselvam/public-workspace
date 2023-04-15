import { VoteComponent } from './vote.component';

describe('VoteComponent', () => {

  let component: VoteComponent;

  beforeEach(() => {
    component = new VoteComponent(); //Arrange
  })

  it('should increment totalVotes when upvoted', () => {
    // let component = new VoteComponent(); //Arrange

    component.upVote(); //Act

    expect(component.totalVotes).toBe(1); //Assert
  });

  it('should decrement totalVotes when downvoted', () => {
    // let component = new VoteComponent(); //Arrange

    component.downVote(); //Act

    expect(component.totalVotes).toBe(-1); //Assert
  });
});
