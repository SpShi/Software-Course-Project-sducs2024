#include<bits/stdc++.h>

using namespace std;
int p; 
const double base_CRIT_rate = 80;
const double base_CRIT_DMG = 176.6;
const int base_HP = 14000;
inline double fuc1(double x, double y)
{
	return (base_HP * (1.16 + (y - x) * 0.0075) + 4780) * (base_CRIT_rate * (x + base_CRIT_DMG) / 100 + 100) / 100;
}
double fuc2(double y)
{
	double l = 0, r, lmid, rmid;
	r = y;
	while (r - l > 1e-6)
	{
		lmid = l + (r - l) / 3.0;
		rmid = l + (r - l) * 2.0 / 3.0;
		double q, w;
		q = fuc1(lmid, y);
		w = fuc1(rmid, y);
		if (q <= w)
		{
			l = lmid;
		}
		else r = rmid;
	}
p=l;
	return fuc1(l,y);
}
int main() {
	double i;
	cin >> i;
	for (double y = 10; y <= i; y += 5) {
		double l = 0, r, lmid, rmid;
		r = y;
		while (r - l > 1e-6)
		{
			lmid = l + (r - l) / 3.0;
			rmid = l + (r - l) * 2.0 / 3.0;
			double q, w;
			q = fuc2(lmid);
			w = fuc2(rmid);
			if (q <= w)
			{
				l = lmid;
			}
			else r = rmid;
		}
		r = p;
		cout << "总分:" << setw(6) << y;
		cout << "生命:" << setw(7) << base_HP * (1.16 + (l - r) * 0.0075) + 4780;
		cout << "生命:" << setw(7) << 0.16 + (l - r) * 0.0075;
		cout << "暴击:" << setw(7) <<base_CRIT_rate << "暴伤:" << setw(7) << r + base_CRIT_DMG ;
		cout << "伤害:" << setw(7) <<(base_HP * (1.16 + (l - r) * 0.0075) + 4780)* (base_CRIT_rate * (r + base_CRIT_DMG) / 100 + 100) / 100<<endl;
	}
	return 0;
}
//[108,351]
