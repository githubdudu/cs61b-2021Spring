```
make check TESTER_FLAGS="--verbose"
make check TESTER_FLAGS="--tolerance=0"
make check TESTER_FLAGS="--verbose --tolerance=0"
```
The default tolerance is 3.

## For run one single test file
python3 testing/tester.py testing/samples/test01-init.in

## For debugging: must run in **/testing folder
python3 runner.py --debug samples/test03-basic-log.in
python3 runner.py --debug student_tests/test02-commit-checkout-log.in
