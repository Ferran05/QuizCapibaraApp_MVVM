import mariadb
import random
from fastapi import FastAPI, HTTPException

app = FastAPI()

@app.get("/questions/{num}")
def get_questions(num: int):
    if num <= 0:
        raise HTTPException(status_code=400, detail="El número a de ser a 0")

    try:
        conn = mariadb.connect(
            host="localhost",
            user="root",
            password="",
            database=""
        )
        cursor = conn.cursor(dictionary=True)

        cursor.execute("SELECT id FROM questions ORDER BY RAND() LIMIT %s", (min(num, 10),))
        question_ids = [row["id"] for row in cursor.fetchall()]

        if not question_ids:
            return []


        placeholders = ",".join("?" for _ in question_ids)
        query = f"""
            SELECT q.id, q.question AS pregunta, a.text, a.is_correct
            FROM questions q
            JOIN answers a ON q.id = a.question_id
            WHERE q.id IN ({placeholders})
        """
        cursor.execute(query, question_ids)
        rows = cursor.fetchall()


        questions_dict = {}
        for row in rows:
            q_id = row["id"]
            if q_id not in questions_dict:
                questions_dict[q_id] = {
                    "id": q_id,
                    "pregunta": row["pregunta"],
                    "respostes": []
                }
            questions_dict[q_id]["respostes"].append({
                "text": row["text"],
                "isCorrect": bool(row["is_correct"]) 
            })


        for question in questions_dict.values():
            random.shuffle(question["respostes"])


        questions_list = list(questions_dict.values())
        random.shuffle(questions_list)

        return questions_list

    except mariadb.Error as e:
        raise HTTPException(status_code=500, detail=f"Error a la base de dades: {e}")
    finally:

        if 'cursor' in locals():
            cursor.close()
        if 'conn' in locals():
            conn.close()