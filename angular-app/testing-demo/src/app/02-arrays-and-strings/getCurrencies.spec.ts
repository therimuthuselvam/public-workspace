import { getCurrencies } from "./getCurrencies";

describe("getCurrencies", () => {
  it("should return the supported currencies", () => {
    const actualResult = getCurrencies();
    expect(actualResult).toContain("USD");
    expect(actualResult).toContain("AUD");
    expect(actualResult).toContain("EUR");
  });
})
