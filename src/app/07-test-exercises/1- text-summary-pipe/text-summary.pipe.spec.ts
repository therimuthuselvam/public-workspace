import { TextSummaryPipe } from './text-summary.pipe';

describe('TextSummarycomponent', () => {

  let component: TextSummaryPipe;

  beforeEach(() => {
    component = new TextSummaryPipe();
  })
  it('should return empty string if the input is null', () => {
    let actualResult = component.transform(null);
    expect(actualResult).toBe('');
  });

  it('should return null if input is undefined', () => {
    expect(component.transform(undefined)).toEqual('');
  });

  it('should return empty string if input is an empty string', () => {
    expect(component.transform('')).toEqual('');
  });

  it('should return the same string if the length of input is less than the limit', () => {
    expect(component.transform('12345', 5)).toEqual('12345');
  });

  it('should summarize the input if it is longer than the limit', () => {
    expect(component.transform('12345678901', 5)).toEqual('12345...');
  });

  it('should assume 10 as the limit if not given', () => {
    expect(component.transform('12345678901')).toEqual('1234567890...');
  });
});
