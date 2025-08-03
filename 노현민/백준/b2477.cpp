#include <iostream>

using namespace std;

int main() {
	int k, nums[6], result,r_m=0,c_m=0,r,c;

	cin >> k;

	for (int i = 0; i < 6; ++i) {
		int tmp;
		cin >> tmp >> nums[i];
		if (tmp==1||tmp==2) {
			if (c_m < nums[i]){
				c_m = nums[i];
				c = i;
			}
		}
		else {
			if (r_m < nums[i]) {
				r_m = nums[i];
				r = i;
			}
		}
	}
	int a = nums[(r + 3) % 6];
	int b = nums[(c + 3) % 6];
	result = ((r_m * c_m)-(a * b))*k;

	cout << result;

}