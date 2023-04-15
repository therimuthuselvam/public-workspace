import { compute } from "./compute";

describe("compute", () => {
  it("compute should return 0 if input is negative", () => {
    const result = compute(-1);
    expect(result).toBe(0);
  })
  it("compute should increment the input if it is positive", () => {
    const result = compute(1);
    expect(result).toBe(2);
  })
})
