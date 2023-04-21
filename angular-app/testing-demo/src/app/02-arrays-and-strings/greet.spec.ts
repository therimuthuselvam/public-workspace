import { greet } from "./greet"

describe("greet", () => {
  it("should include the name in the message", function () {
    // const expectedResult = "Welcome Theri"
    // const actualResult = greet("Theri");
    // expect(actualResult).toMatch(expectedResult);
    expect(greet("Theri")).toContain("Theri");
  })
})
