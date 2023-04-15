import { VoteComponent } from './vote.component';

describe('VoteComponent', () => {
  let component: VoteComponent;

  beforeEach(() => {
    component = new VoteComponent();
  });

  it('should raise voteChanged event when upvoted', () => {
    let totalVotes: any = null;
    component.voteChanged.subscribe(tv => totalVotes = tv) // Arrange

    component.upVote(); //Act

    // expect(totalVotes).not.toBeNull(); // It's a generic check //Assert
    expect(totalVotes).toBe(1); // It's a specific check //Assert
  });
});
