class Solution:
    def solution(self, nums: List[int]) -> int:

        b1, b2 = 0, 0

        for i in nums:

            b2 |= (b1 & i)
            b1 ^= i

            shared = (b1 & b2)
            b1 ^= shared
            b2 ^= shared

        return b1