<script type="text/x-template" id="tariff-row-template">
    <div>
        <template v-if="isAdmin">
            <tariff-row-admin
                :id="id"
                :title="title"
                :description="description"
                :number_of_days="days"
                :cost="cost"
                :currency="currency"

                :subscribers="subscribers"
            ></tariff-row-admin>
        </template>

        <template v-if="!isAdmin">
            <tariff-row-subscriber
                :id="id"
                :title="title"
                :description="description"
                :number_of_days="days"
                :cost="cost"
                :currency="currency"

                :banned="is_banned"
                :subscriptions="subscriptions"
                :user_id="user_id"
            ></tariff-row-subscriber>
        </template>
    </div>
</script>

<script>
    Vue.component('tariff-row', {
        props: [
            'id', 'title', 'description', 'days', 'cost', 'currency',
            'subscribers', 'is_admin', 'subscriptions', 'user_id', 'is_banned',
        ],
        data() {
            return {
                isAdmin: this.is_admin == "admin"
            }
        },
        mounted() {

        },
        template: "#tariff-row-template",
    })
</script>