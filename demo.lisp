(+ 2 3)
(* 5 (/ 2.0 3))
((lambda (x) (* x x)) 3)
(let ((x 2)) (let ((y 3)) (+ x y)))
(((lambda (x) (lambda (y) (- x y))) 2) 3)
(let ((x 2)) (let ((x (+ x 3))) x))
(let ((f (lambda (x) (+ x x)))) (f 3))
(let ((f (lambda (x) (* x 3)))) (let ((f (lambda (y) (- y 1)))) (f 2)))
((lambda (H) ((lambda (f) ((lambda (d/dx) (d/dx 10)) (lambda (x) (/ (- (f (+ x H)) (f x)) H)))) (lambda (x) (* x x)))) .001)
((lambda (x) (x x)) (lambda (x) x))

; dynamic vs. static scoping
(let ((x 1)) (let ((f (lambda (y) (+ y x)))) (let ((x 2)) (f 3))))
